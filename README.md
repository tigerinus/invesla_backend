# Invesla

```SQL
create user invesla_sa identified by "q1w2e3r4Q!W@E#R$";

grant create session to invesla_sa;
grant create table to invesla_sa;

alter user invesla_sa quota unlimited on data;
```
