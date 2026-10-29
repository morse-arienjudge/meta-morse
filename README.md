# meta-morse: A Yocto layer for Morse Micro software

This repo integrates Morse Micro software (Morse release mm8108-2.1.0) into the Variscite fork of Yocto Scarthgap based on NXP BSP L6.6.52_2.2.2. It has been tested to work with a Variscite 6UL-Customboard. Adaptations can be made to get it to work with other target architectures.

## Environment set-up

Follow the guide provided by Variscite at https://dev.variscite.com/dart-6ul/mx6ul-yocto-scarthgap-6.6.y_2.2.2-v1.3/yocto-build-release/#setup-and-build-yocto. It is recommended to do a long, tedious build here first.

## Adding the meta-morse layer

```
cd ~/yocto/sources
git clone https://github.com/MorseMicroLabs/meta-morse.git --branch=variscite-imx6-scarthgap
```
This layer includes recipes for The Morse Driver (morse-driver), hostapd_s1g (morse-hostap), command line (morse-cli), and for deploying firmware binaries (morse-bin). It also includes recipes for NetworkManager to concurrently support wpa_supplicant_s1g and standard wpa_supplicant.

In your local.conf add
```
IMAGE_INSTALL:append = " morse-driver morse-bin morse-cli morse-hostap morse-wpa-supplicant"
```
to include all of the Morse Micro core software packages.

## Building

You can now rebuild the image with ```bitbake core-image-base```.
