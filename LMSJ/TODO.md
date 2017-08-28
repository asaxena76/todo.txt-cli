# TODOS

  * Accounts remaining

   ** Bank of the west
   ** Macys

  * Generate the correct rules based on what I have for the whole year

    ** Pull transaction

    ** Put some rules based on sender

    ** Apply rules - Generates transactions

    ** Repeat

  * Use the rules to create a chart of account

  * Generate the ledger format transactions

    I keep putting this off because its too cumbersome to add transactions
    from a daily report when I don't have any categories. So probably its better
    to create a chart of accounts then go from there


############

## Make this hosted and deployable from bitbucket

# Stories

## Run on local postgres and make sure data is backed up daily

## Every evening I should be able to get a view of the day
   Ledger daily report can be generated and sent using email .
   For this to work I need to be able to run ledger on lambda or ec 2
   Well I just need a summary of todays transactions and the categories.

   lambda is cheaper but i will have to use dynamodb

### How many transactions have happened today
   See above


### I should be able to categorize them
   I need to put them into text format and categorize them in the evening
   if my auto category is not working

### View should be available on both mobile and desktop

## Every day I should be see rolling budget

### Should be able to generate my chart of accounts at any point of time.
### Future outlook should be available



## Specific questions or queries

### If I have all the transactions in ledger format

### then I should be able to do all these queries and more.

### How much money do we have to plan for vacation to India

### How much money have we spent in a month and how much money we need

### How much money have we spent in this year in each of the categories


## How much money have we spent on business

## How much money is going to come

## How much we still need to spend on business

