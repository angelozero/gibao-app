# gibao-app
![gibraltar](https://i.imgur.com/VpZHPVY.jpg)


# Commands line for Postgres

#### 1. Enlisting the available databases
 - You can use the \l command to get a list of all available databases.

#### 2. Enlisting the available tables in the current database
- The next command does this for you 
- ```\dt ```

#### 3. Switching to another database
- The syntax for doing this: \c <database_name>. Suppose, you want to switch to a database named datacamp_tutorials you can do so like the following -
- ```\c datacamp_tutorials``` 


#### 4. Describing a particular table
 - The general syntax for doing is ```\d <table_name>```. Suppose, you are in the datacamp_tutorials database and you want to describe the table named countries. 
 - The command for this would be 
 - ```\d countries ```

 #### For more examples ---> [10 Command-line Utilities in PostgreSQL](https://www.datacamp.com/community/tutorials/10-command-line-utilities-postgresql?utm_source=adwords_ppc&utm_campaignid=1455363063&utm_adgroupid=65083631748&utm_device=c&utm_keyword=&utm_matchtype=b&utm_network=g&utm_adpostion=&utm_creative=332602034361&utm_targetid=aud-392016246653:dsa-429603003980&utm_loc_interest_ms=&utm_loc_physical_ms=1001773&gclid=Cj0KCQiAnKeCBhDPARIsAFDTLTIrlsOiY9m31jKm5SeMNVmaG4bMojBbJlhKQAVyLgdV5ueb_xYd3cgaAvm-EALw_wcB)