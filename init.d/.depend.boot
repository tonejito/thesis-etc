TARGETS = mountkernfs.sh udev mountdevsubfs.sh bootlogd keyboard-setup hostname.sh hwclockfirst.sh console-setup hwclock.sh checkroot.sh mountall.sh mountoverflowtmp ifupdown ifupdown-clean mountnfs.sh mountnfs-bootclean.sh networking urandom checkfs.sh mtab.sh procps module-init-tools udev-mtab kbd bootmisc.sh stop-bootlogd-single mountall-bootclean.sh
INTERACTIVE = udev keyboard-setup console-setup checkroot.sh checkfs.sh kbd
udev: mountkernfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
bootlogd: mountdevsubfs.sh
keyboard-setup: mountkernfs.sh udev bootlogd
hostname.sh: bootlogd
hwclockfirst.sh: mountdevsubfs.sh bootlogd
console-setup: mountall.sh mountoverflowtmp mountnfs.sh mountnfs-bootclean.sh kbd
hwclock.sh: checkroot.sh bootlogd
checkroot.sh: mountdevsubfs.sh hostname.sh hwclockfirst.sh bootlogd keyboard-setup
mountall.sh: checkfs.sh
mountoverflowtmp: mountall-bootclean.sh
ifupdown: ifupdown-clean
ifupdown-clean: checkroot.sh
mountnfs.sh: mountall.sh mountoverflowtmp networking ifupdown
mountnfs-bootclean.sh: mountall.sh mountoverflowtmp mountnfs.sh
networking: mountkernfs.sh mountall.sh mountoverflowtmp ifupdown
urandom: mountall.sh mountoverflowtmp
checkfs.sh: checkroot.sh mtab.sh
mtab.sh: checkroot.sh
procps: mountkernfs.sh mountall.sh mountoverflowtmp udev module-init-tools bootlogd
module-init-tools: checkroot.sh
udev-mtab: udev mountall.sh mountoverflowtmp
kbd: mountall.sh mountoverflowtmp mountnfs.sh mountnfs-bootclean.sh
bootmisc.sh: mountall.sh mountoverflowtmp mountnfs.sh mountnfs-bootclean.sh udev
stop-bootlogd-single: mountall.sh mountoverflowtmp udev keyboard-setup console-setup hwclock.sh checkroot.sh ifupdown ifupdown-clean mountnfs.sh mountnfs-bootclean.sh networking mountkernfs.sh urandom hostname.sh checkfs.sh mtab.sh procps module-init-tools mountdevsubfs.sh hwclockfirst.sh bootlogd udev-mtab kbd bootmisc.sh mountall-bootclean.sh
mountall-bootclean.sh: mountall.sh
