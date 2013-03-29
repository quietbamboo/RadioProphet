export NDK_ROOT=/home/hjx/RadioProphet/android-ndk-r8d
export SYSROOT=$NDK_ROOT/platforms/android-14/arch-arm
export TOOLCHAIN=$NDK_ROOT/toolchains/arm-linux-androideabi-4.7/prebuilt/linux-x86/bin
export PATH=$NDK_ROOT:$TOOLCHAIN:$PATH
arm-linux-androideabi-gcc  -o cpu -g cpu.c --sysroot $SYSROOT
