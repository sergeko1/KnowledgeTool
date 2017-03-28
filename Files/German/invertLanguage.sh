#usage ./$0 originFile destinationFile
cat $1| awk 'BEGIN {FS="@"} {print $2,"@",$1}' | sed s/" @ "/"@"/  > $2
