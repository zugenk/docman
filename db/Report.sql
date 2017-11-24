/*
select done_by, entity,date_trunc('day', audit_time) AS "Month", count(id) as Freq from audit_trail 
where date_trunc('month', audit_time)='2017-11-01'
group by 1,2,3
*/
--select date_trunc('month', audit_time) from audit_trail where id=1
-- select count(id), user_id, status,login_time from login_history group by user_id, status, login_time
/*
SELECT date_trunc('day', login_time) AS "Month" , user_id, count(*) AS "freq"
FROM login_history
where date_trunc('month', login_time)='2017-11-01'
-- WHERE login_time > now() - interval '1 month' 
GROUP BY 1,2
ORDER BY 1; */

SELECT date_trunc('day', login_time) AS 'date' , user_id, count(*) AS Freq FROM login_history  WHERE login_time > now() - interval '1 month'  GROUP BY 1,2  ORDER BY 1;