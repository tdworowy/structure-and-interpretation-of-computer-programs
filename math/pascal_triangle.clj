(defn pascal [row, col]
  (cond
    (= row 1) 1
    (or (= col 1) (= col row)) 1
    :else (+ (pascal (- row 1) (- col 1)) (pascal (- row 1) col))))

(defn display-pascal-row [n]
  (defn column-iter [i]
    (print (pascal n i)) (print "  ")
    (if (= i n)
      (newline)
      (column-iter (+ i 1))))
  (column-iter 1))

(defn display-pascal [n]
  (defn display-pascal-iter [i]
    (display-pascal-row i)
    (if (= i n)
      (newline)
      (display-pascal-iter (+ i 1))))
  (display-pascal-iter 1))

(display-pascal 14)