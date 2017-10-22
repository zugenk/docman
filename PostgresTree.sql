/* Working Upward/Downward tree tested
WITH RECURSIVE q AS (
   SELECT forum.id, forum.code,forum.name, forum.parent_forum
   FROM forum
   WHERE forum.id=4  --//starting id
		UNION ALL
   SELECT x.id, x.code,x.name, x.parent_forum
   FROM forum  x
    -- JOIN q ON q.parent_forum = x.id)  --// Upward Tree
      JOIN q ON q.id = x.parent_forum) --// Downward Tree
   SELECT * FROM q 
      
-- */       

/* Working Full Tree tested        
WITH RECURSIVE frm AS (
   SELECT forum.id as id, forum.code,forum.name,forum.id||'' as tree, COALESCE(forum.parent_forum,0) as parent_forum, 0 AS level
   FROM forum 
    	--WHERE forum.id=1 --//Starting rootId
    	WHERE forum.parent_forum is null  --//FullTree
   UNION  ALL
   SELECT f.id as id, f.code,f.name,(c.tree||'.'||f.id) as tree, COALESCE(f.parent_forum,0) as parent_forum, (c.level + 1) as level
   FROM frm  c    
   JOIN forum f ON f.parent_forum = c.id
   )
SELECT *
FROM   frm
ORDER  BY  frm.tree --id ASC, frm.parent_forum asc, frm.level ASC;

-- */