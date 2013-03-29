#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <time.h>
#include <sys/timeb.h>
#include <sys/stat.h>

typedef struct {
	unsigned int cpu_total;
	unsigned int cpu_usage;
} CPU_USAGE;

double GetCPUInfo(CPU_USAGE * pCU) {
	static char buf[32];
	
	unsigned int cpu_user, cpu_nice, cpu_kernel, cpu_idle, cpu_iowait, cpu_irq, cpu_softirq;
	unsigned int cpu_usage, cpu_total;
	
	FILE * ifs = fopen("/proc/stat", "r");
	if (ifs == NULL) {
		printf("Error: cannot open /proc/stat\n");
		exit(0);
	}
		
	fscanf(ifs, "%s %u %u %u %u %u %u %u",
		buf,
		&cpu_user, &cpu_nice, &cpu_kernel, &cpu_idle, &cpu_iowait, &cpu_irq, &cpu_softirq
	);
	
	fclose(ifs);
	
	cpu_total = cpu_user + cpu_nice + cpu_kernel + cpu_idle + cpu_iowait + cpu_irq + cpu_softirq;
	cpu_usage = cpu_total - cpu_idle;
	
	if (pCU->cpu_total == 0) {
		pCU->cpu_total = cpu_total;
		pCU->cpu_usage = cpu_usage;
		return -1.0f;
	} else {
		double u = (double)(cpu_usage - pCU->cpu_usage) / (cpu_total - pCU->cpu_total);
		pCU->cpu_total = cpu_total;
		pCU->cpu_usage = cpu_usage;		
		return u;
	}
}

int main(int argc, char * * argv) {
	CPU_USAGE cu;
	double u;
	
	cu.cpu_total = 0;
	cu.cpu_usage = 0;
	
	if (argc != 2) {
		printf("Usage: %s [interval in ms]\n", argv[0]);
		exit(0);
	}
	
	int p = atoi(argv[1]) * 1000;
	
	
	GetCPUInfo(&cu);
	usleep(p);
		
	struct timeb ts;
		
	while (1) {
		u = GetCPUInfo(&cu);
		
		ftime(&ts);	
		
		fprintf(stdout, "%.3lf %.2lf\n", ts.time + ts.millitm / (double) 1000.0f, u);
		usleep(p);		
	}
	
	return 0;
}
