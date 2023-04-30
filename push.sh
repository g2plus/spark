#/bin/bash
target='target'
idea='.idea'
iml='.iml'
GITADD(){
	for item in `ls`;do
		# 判断item的值是否为src,如果为target目录不进行添加 
		if [ -d $item ] && [[ ${item:-1,6} != ${target} && ${item:-1,5} != ${idea} ]] 
		then
			echo 'enter into dir' $item 
			cd $item
			GITADD $item 
			cd ..
			echo 'leave dir' ${item}
		elif [ ${item:-1,4} != ${iml} ] && [ -f ${item} ]
		then
			echo $item 'is being processed'
			git add $item
		else
			echo ''
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
