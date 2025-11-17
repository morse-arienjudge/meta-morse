DESCRIPTION = "Build and install the morse driver"

TAG_NAME = "1.15.3"
PV = "${TAG_NAME}+git${SRCPV}"
SRC_URI = "git://github.com/MorseMicro/morse_driver.git;protocol=https;branch=main;tag=${TAG_NAME};submodules=1"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit module

RPROVIDES:${PN} += "kernel-module-morse kernel-module-dot11ah"

S = "${WORKDIR}/git"
B = "${S}"

do_configure() {
	sed -i 's/$(MAKE) MORSE_VERSION=$(MORSE_VERSION) -C $(KERNEL_SRC) M=$(SRC)/$(MAKE) MORSE_VERSION=$(MORSE_VERSION) -C $(KERNEL_SRC) CFLAGS_MODULE="-Wno-enum-int-mismatch" M=$(SRC)/' Makefile
}

do_compile() {
	bbnote $(pwd)
	oe_runmake CONFIG_WLAN_VENDOR_MORSE=m CONFIG_MORSE_SDIO_ALIGNMENT=4 CONFIG_MORSE_SDIO=y CONFIG_MORSE_SPI=y CONFIG_MORSE_USB=y CONFIG_MORSE_USER_ACCESS=y CONFIG_MORSE_VENDOR_COMMAND=y CONFIG_MORSE_ENABLE_TEST_MODES=y
}

do_install() {
	install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/

	install -m 0644 morse.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/morse.ko
	install -m 0644 dot11ah/dot11ah.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/dot11ah.ko
}

#FILES:${PN} += "${nonarch_base_libdir}/modules/${KERNEL_VERSION}/morse.ko ${nonarch_base_libdir}/modules/${KERNEL_VERSION}/dot11ah.ko"
