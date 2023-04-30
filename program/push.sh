#/bin/bash
exclude='target'
GITADD(){
	for item in `ls`;do
		# 判断item的值是否为src,如果为target目录不进行添加 
		if [ -d $item ] && [ ${item:-1,6} != ${exclude} ] 
		then
			cd $item
			GITADD $item 
			cd ..
		else
			git add $item
		fi
	done
}
GITADD $1 #$1 the folder to be added
read -p "Please enter commit information > " message
touch temp #create temporary file to store cmd
echo "#!/bin/bash">>temp
echo "git commit -m '${message}'">>temp
bash temp
rm -rf temp
read -p "Please enter the branch name>" branch
git push -u origin $branch
