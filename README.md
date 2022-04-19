# Invesla

## User and permission setup

```SQL
create user invesla_sa identified by "Q!W@E#R$q1w2e3r4";

grant create session to invesla_sa;
grant create table to invesla_sa;

alter user invesla_sa quota unlimited on data;
```

## Views

### INVESLA_QUOTES_VIEW

```SQL
create view INVESLA_QUOTES_VIEW as
    select symbol, last_trade_price
    from invesla_quotes q, invesla_stocks s
    where q.symbol_id = s.symbol_id
```

### INVESLA_STOCK_DETAIL_PREV_3_DAYS_VIEW

```SQL
create view invesla_stock_detail_prev_3_days_view as
    select
        s.symbol,
        d3.date_stamp prev_date_3, d3.prev_day_close_price prev_day_3, 
        d2.date_stamp prev_date_2, d2.prev_day_close_price prev_day_2, 
        d1.date_stamp prev_date_1, d1.prev_day_close_price prev_day_1
    from
    invesla_stocks s
    full outer join
    (
        select symbol_id, prev_day_close_price, date_stamp from invesla_stock_detail where date_stamp in
        (
            select date_stamp from
            (
                select date_stamp, rownum as rn from (
                    select unique date_stamp from invesla_stock_detail order by date_stamp desc fetch first 3 rows only
                )
            ) where rn=1
        )
    ) d1
    on s.symbol_id = d1.symbol_id
    full outer join
    (
        select symbol_id, prev_day_close_price, date_stamp from invesla_stock_detail where date_stamp in
        (
            select date_stamp from
            (
                select date_stamp, rownum as rn from (
                    select unique date_stamp from invesla_stock_detail order by date_stamp desc fetch first 3 rows only
                )
            ) where rn=2
        )
    ) d2
    on s.symbol_id = d2.symbol_id
    full outer join
    (
        select symbol_id, prev_day_close_price, date_stamp from invesla_stock_detail where date_stamp in
        (
            select date_stamp from
            (
                select date_stamp, rownum as rn from (
                    select unique date_stamp from invesla_stock_detail order by date_stamp desc fetch first 3 rows only
                )
            ) where rn=3
        )
    ) d3
    on s.symbol_id = d3.symbol_id
```

## Docker

```bash
docker run --name invesla --restart always -d -v /path/to/wallet:/app/wallet -e ORACLE_DB_WALLET_PATH=/app/wallet wangxiaohu/invesla:$VERSION
```

## Trouble shooting

- org.hibernate.HibernateException: Access to DialectResolutionInfo cannot be null when 'hibernate.dialect' not set

  - ensure DB_PASS is correct
  - when new Questrade token is needed, might need to delete the token row from DB first before refreshing
  - make sure `.vscode/launch.json` and `.vscode/setting.json` has correct `QT_TOKEN_KEY` set in `env`
  - might need to create a new token on Questrade app hub
