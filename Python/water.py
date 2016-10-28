#/usr/bin/env python3
#-*- coding = utf-8 -*-

import os, io, sys, re, time, json, random
from PIL import Image, ImageEnhance, ImageFilter, ImageFont, ImageDraw

fPath = 'C:\\Users\\KIM\\Desktop\\'

def text2img(text, font_color="Blue", font_size=25):
    
	# 生成内容为 TEXT 的水印
	font = ImageFont.truetype(fPath+'my.ttc', font_size)
	# 多行文字处理
	text = text.split('\n')
	mark_width = 0
	for  i in range(len(text)):
		(width, height) = font.getsize(text[i])
		if mark_width < width:
			mark_width = width
	mark_height = height * len(text)

    # 生成水印图片
	mark = Image.new('RGBA', (mark_width,mark_height))
	draw = ImageDraw.ImageDraw(mark, "RGBA")
	draw.setfont(font)
	for i in range(len(text)):
		(width, height) = font.getsize(text[i])
		draw.text((0, i*height), text[i], fill=font_color)
	return mark

def watermark(img_source, img_water, img_new, offset_x, offset_y):
	try:
		im = Image.open(img_source)
		wm = Image.open(img_water)
		layer = Image.new('RGBA', im.size, (0,0,0,0))
		layer.paste(wm, (im.size[0] - offset_x, im.size[1] - offset_y))
		newIm = Image.composite(layer, im, layer)
		newIm.save(img_new)

	except Exception as e:
		print(">>>>>>>>>>> WaterMark EXCEPTION:  " + str(e))
		return False
	else:
		return True

def main():
	text = u'ShangHai University'
	mark = text2img(text)
	mark.save(fPath + "character.png", "PNG")
	watermark(fPath + 'tmp.jpg', fPath + 'character.png', fPath + 'afterwater.jpg', 250, 250)

if __name__ == '__main__':
	main()

