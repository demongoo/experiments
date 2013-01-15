(* P01: Find the last element of a list *) 

(* recursive *)
fun last(xs: 'a list) =
  if null (tl xs)
  then hd xs
  else last (tl xs)

(* test *)
val ls = [1, 2, 3, 4]
val x = last ls