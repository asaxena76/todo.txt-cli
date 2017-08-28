#!/bin/bash

input="category_list.csv"
while  read -r l
do
  echo "$var"
echo "
rule \"$l\"
when
   tx : PlaidTransaction( plaidCategory.hierarchy == \"$l\" )
then
   System.out.println( tx.plaidCategory.hierarchy + " " + tx.txname );
end "
done < "$input"