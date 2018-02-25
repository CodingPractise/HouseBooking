//To identify free rooms for a particular date
SELECT c_room_id FROM t_room WHERE 
c_room_id NOT IN
(
  SELECT rr.c_room_id FROM 
  t_room_reservation as rr INNER JOIN t_reservation r
  ON rr.c_reservation_id = r.c_reservation_id
  AND rr.c_status <> 0
  WHERE 
  ( 
    ( r.c_booked_to >= '2018-02-18' AND r.c_booked_to <= 

'2018-02-21')
     OR
    ( r.c_booked_from >= '2018-02-18' AND r.c_booked_from 

<= '2018-02-21')
     OR
    ( r.c_booked_from <= '2018-02-18' AND r.c_booked_to >= 

'2018-02-21')
  ) 
);