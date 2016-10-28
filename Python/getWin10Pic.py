import os
import os.path

# 指明被遍历的文件夹
rootdir = 'C:\\Users\\KIM\\AppData\\Local\\Packages\\Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\\LocalState\Assets'

newName = 0
for parent,dirnames,filenames in os.walk(rootdir):
	for filename in filenames:
		if (len(filename) <= 30):
			fName = filename.split('.')
			if (fName[0].isdigit() and int(fName[0]) > int(newName)):
				newName = fName[0]

for parent,dirnames,filenames in os.walk(rootdir):    #三个参数：分别返回1.父目录 2.所有文件夹名字（不含路径） 3.所有文件名字
	for filename in filenames:                        #输出文件信息
		#print("the full name of the file is:", os.path.join(parent,filename)) #输出文件路径信息 307200/300K
		#print("filename is: ", filename)
		#print("filename size is: ", len(filename))

		myStr = os.path.join(parent, filename)
		if (os.path.getsize(myStr) > 307200):
			if (len(filename) >= 30):
				newName += 1
				tmp = str(newName)
				# os.rename(filename, tmp + '.jpg')
				os.rename(os.path.join(parent, filename), os.path.join(parent, tmp + '.jpg'))

print('Done!')
