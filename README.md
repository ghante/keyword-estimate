# keyword-estimate

### To run the Application :
  ```./gradlew bootRun```
##### OR
1. build : ```./gradlew build ``` 
2. run : ```java -jar ./build/libs/keyword-estimate-0.0.1-SNAPSHOT.jar```

### Sample request and response :
```http://localhost:8080/estimate?keyword=lenovo```
```
{
score: 41
}
```

### Solution description :
Solution queries the amazon autocomplete api in a loop with longest common prefix of results + next character from keyword. With every search, score goes down by 10. Once found, the score is adjusted to the corresponding index of keyword in search results. For example to lookup “samsung note 9” it will perform following searches:
1. S
2. Sa
3. Sam
4. Samsung n
So, the score is : 100 - 40 (for 4 searches) - 7(index in the 4th search) = 53

If keyword is not found by an exact match, but if it is a prefix of all the search results, 36 is subtracted from the score. 36 = 26 (for a-z) + 10 (for 0-9).


