#/usr/bin/env python3
#-*- coding = utf-8 -*-

import os, io, sys, re, time, json, random
from PIL import Image, ImageEnhance, ImageFilter, ImageFont, ImageDraw

im1 = Image.open('C:\\Users\\KIM\\Desktop\\character.png')
im2 = im1.rotate(90, expand=1)
im2.save('done.jpg')

