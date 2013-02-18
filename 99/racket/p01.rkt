; P01: Find the last element of a list
#lang racket

; recursive
(define (last-1 xs)
  (if (null? (cdr xs)) (car xs) (last (cdr xs))))

; built-in
(define (last-2 xs) (last xs))

; tests
(define xs (list 1 2 3 4))
(write (last-1 xs))
(write (last-2 xs))