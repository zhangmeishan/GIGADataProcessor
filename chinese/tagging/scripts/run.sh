#!/bin/bash
cd stanford-postagger-full-2015-12-09/

folder=$1

mkdir ../../data/$folder/

for i in `find ../../../segmentation/data/$folder -name *.seg`;
do  
    # echo $i
    echo data/$folder/`basename $i .seg`.tag
    java -mx300m -cp 'stanford-postagger.jar:lib/*' edu.stanford.nlp.tagger.maxent.MaxentTagger -model models/chinese-nodistsim.tagger -tagSeparator _ -textFile $i > ../../data/$folder/`basename $i .seg`.tag
done
