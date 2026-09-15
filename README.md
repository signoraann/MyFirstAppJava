## Environment variables

The app reads these at startup and refuses to run if any is missing:

| Variable      | Example                                        |
|:--------------|:-----------------------------------------------|
| `DB_URL`      | `jdbc:postgresql://localhost:5432/mydatabase ` |
| `DB_USER`     | `my_user`                                      |
| `DB_PASSWORD` | `my_password`                                  |

Set them in IntelliJ: **Run** -> **Edit Configurations** -> **Environment variables**.