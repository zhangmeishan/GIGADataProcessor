#!/bin/bash
cd ZPar-Meishan/

folder=$1

mkdir ../../data/$folder/

for i in `find ../../../tagging/data/$folder -name *.tag`;
do  
    # echo $i
    echo data/$folder/`basename $i .tag`.dep
    ./dist/chinese.depparser/depparser  $i ../../data/$folder/`basename $i .tag`.dep  models/ctb51/model.41
done
