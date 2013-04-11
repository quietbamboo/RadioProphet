user = '351863045071962';
algo = 'classification';
rate = 0.001;
L = 1000;
B = 1;
L2 = '1000';
B2 = '1';
A = load(['input.' user]);
[m, n] = size(A);
out=fopen([algo '.' user '.' L2 '.' B2 '.out'], 'w');
for i = 0:((m-L-B)/B);
	R = rand(1);
	if R <= rate
		%X = A((1 + i * B) : (i * B + L), [1 4 7 10 13]);
		X = A((1 + i * B) : (i * B + L), [1:18]);
		if strcmp(algo, 'regression')
			Y = A((1 + i * B) : (i * B + L), 19);
		else
			Y = A((1 + i * B) : (i * B + L), 20);
		end

		if strcmp(algo, 'bayes')
			model = NaiveBayes.fit(X, Y, 'Distribution', 'mvmn');
		elseif strcmp(algo, 'discriminant')
			model = ClassificationDiscriminant.fit(X, Y, 'discrimType', 'pseudoLinear');
		elseif strcmp(algo, 'regression')
			model = RegressionTree.fit(X, Y, 'CategoricalPredictors', 'all');
		elseif strcmp(algo, 'classification')
			model = ClassificationTree.fit(X, Y, 'CategoricalPredictors', 'all');
		elseif strcmp(algo, 'svm')
			model = svmtrain(X, Y, 'Kernel_Function','mlp');
		elseif strcmp(algo, 'robustBoost')
			model = fitensemble(X, Y, 'RobustBoost', 20, 'Tree', 'CategoricalPredictors', 'all', 'RobustErrorGoal',0.15,'RobustMaxMargin',1);
		elseif strcmp(algo, 'ensemble')
			model = fitensemble(X, Y, 'Bag', 20, 'Tree', 'type','classification', 'CategoricalPredictors', 'all');
		end

		for j = 1:B;
			if strcmp(algo, 'svm')
				res = svmclassify(model, A(i * B + L + j, [1:18]));
				%res = svmclassify(model, A(i * B + L + j, [1 4 7 10 13]));
			else
				res = predict(model, A(i * B + L + j, [1:18]));
				%res = predict(model, A(i * B + L + j, [1 4 7 10 13]));
			end
			fprintf(out, '%1.5f %1.5f %1.5f %d %d %d %d\n', A(i * B + L + j, 19), A(i * B + L + j, 20), res, A(i * B + L + j, 21), A(i * B + L + j, 22), A(i * B + L + j, 23), A(i * B + L + j, 24));
		end;
	end;
end;
fclose(out);
