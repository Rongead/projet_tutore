#!/bin/bash

if [ $# -ne 2 ]
then
	echo "Usage: $0 source racine"
	exit 1
fi

if [ ! -r $1 ]
then
	echo "Source n'est pas un fichier lisible"
	exit 1
fi

if [ ! -d $2 ]
then
	echo "Répertoire de destination invalide"
	exit 1
fi

i=0

while( i<2 )
do
	cat "" >temp.java
	for x in generateur.java
	do
		if grep "^source" $x
		then
			cat "source = $1" >>temp.java
			((i= $i + 1))
			break
