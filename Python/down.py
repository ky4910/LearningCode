#/usr/bin/env python3
#-*- coding = utf-8 -*-

import os
import urllib.request

filename = 'C:\\Users\\KIM\\Desktop\\photo'
iName = 'F:\\Code\\CSharp\\newPhoto\\s'
Num = 0

pFile = open(filename, "r")

while True:
	line = pFile.readline()
	if line:
		with urllib.request.urlopen(line) as url:
			data = url.read()
		iFile = open(iName + str(Num) + '.jpg', 'wb')
		iFile.write(data)
		iFile.close()
		Num += 1
		# print (line)
	else:
		break

pFile.close()	



