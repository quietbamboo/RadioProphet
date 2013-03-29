#prepare environment
sudo ~/RadioProphet/usr/local/openmoko/arm/setup-env

#compile tcpdump
#cd libpcap-1.3.0/
#CC=arm-angstrom-linux-gnueabi-gcc ac_cv_linux_vers=2 ./configure --host=arm-linux --with-pcap=linux
#make clean
#make
#cd ..

#compile tcpdump
cd tcpdump-4.3.0/
CC=arm-angstrom-linux-gnueabi-gcc ac_cv_linux_vers=2 ./configure --host=arm-linux --with-pcap=linux
#Edit the makefile, remove the -O2 flag and add the -static flag to the linker (LD_FLAGS += -static)
make clean
make
cd ..

cp tcpdump-4.3.0/tcpdump .
