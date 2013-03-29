#macbook:
#modify code
rsync -a --exclude=.git . hjx@koala.eecs.umich.edu:~/RadioProphet/

#koala:
ssh hjx@koala.eecs.umich.edu 'export PATH=/home/hjx/RadioProphet/usr/local/openmoko/arm/bin:/home/hjx/RadioProphet:$PATH;cd ~/RadioProphet/;bash c.sh'

#macbook:
scp hjx@koala.eecs.umich.edu:~/RadioProphet/tcpdump .
adb push tcpdump /data/local/
