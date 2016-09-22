#!/bin/bash


TARGET_DATA_DIR=$(dirname $0)/target/data
# delete old symbolic link if exists
if [[ -L "$TARGET_DATA_DIR" &&  -d "$TARGET_DATA_DIR" ]]; then
	rm -v "TARGET_DATA_DIR"
fi

# create new symbolic link
ln -s $(dirname $0)/data $TARGET_DATA_DIR

# start castiel
java -jar $(dirname $0)/target/castiel-1.0.jar $*
