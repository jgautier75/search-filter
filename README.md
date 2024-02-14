# search-filter

[Microsoft REST API](https://github.com/microsoft/api-guidelines/blob/vNext/azure/Guidelines.md) filter implementation in JAVA using [ANTLR4](https://github.com/antlr/antlr4/blob/master/doc/getting-started.md)



| Operator  | Description  | Example  |
|---|---|---|
| eq  | Equals  | 'APPLE' |
| ne  | Not equals  | 'BANANA'  |
| gt  | Greater than  | price gt 100  |
| ge  | Greater or Equals than  | price ge 100  |
| lt  | Lower than  | price lt 100  |
| le  | Lower or Equals  | price le 100  |
| lk  | Like  | fruit lk 'bana'  |
| and  | Logical and | price le 200 and price gt 3.5  |
| or  | Logical or | price le 200 or price gt 3.5  |
| not  | Logical negation | not price lt 200 |
| ()  | Grouping | ( fruit eq 'APPLE' or fruit eq 'BANANA')  |

ANTLR definition: src/main/antlr4/com/acme/jga/filtering/antlr/Filter.g4

Generate POJO from ANTLR using the followin command:

```sh
mvn antlr4:antlr4
```

Tests: src/test/java/com/acme/jga/filtering/BasicTest