#!/bin/bash
cd ZPar-Meishan/

folder=$1

mkdir -p ../../data/$folder/
mkdir -p ../../tag/$folder/

for i in `find ../../../tagging/data/$folder -name *.tag`;
do  
    echo 'read file ' $i

    echo 'write tagged file ' ../../tag/$folder/`basename $i`
    sed  's/\_URL/\_NR/g' $i | sed  's/\_X/\_M/g' > ../../tag/$folder/`basename $i`

    echo 'write parsed file ' ../../data/$folder/`basename $i .tag`.dep 
    ./dist/chinese.depparser/depparser  ../../tag/$folder/`basename $i` ../../data/$folder/`basename $i .tag`.dep  models/ctb51/model.41
done
