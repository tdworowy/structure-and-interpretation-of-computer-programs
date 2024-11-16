
(defn gcd [a b]
  (if (zero? b)
    a
    (recur b (mod a b))))

(defn make-rat [n d]
  (let [g (gcd n d)
        denom-sign (if (> d 0) 1 -1)]
    (list (* (/ n g) denom-sign)
          (* (/ d g) denom-sign))))

(defn numer [x] (first x))
(defn denom [x] (first (rest x)))

(defn add-rat [x y]
  (make-rat (+ (* (numer x) (denom y)) (* (numer y) (denom x))) (* (denom x) (denom y))))

(defn sub-rat [x y]
  (make-rat (- (* (numer x) (denom y)) (* (numer y) (denom x))) (* (denom x) (denom y))))

(defn mul-rat [x y]
  (make-rat (* (numer x) (numer y)) (* (denom x) (denom y))))

(defn div-rat [x y]
  (make-rat (* (numer x) (denom y)) (* (denom x) (numer y))))

(defn equal-rat? [x y]
  (= (* (numer x) (denom y)) (* (numer y) (denom y))))

(defn print-rat [x]
  (newline)
  (print (numer x))
  (print "/")
  (print (denom x)))

(def one-half (make-rat 1 2))
(print-rat one-half)

(print-rat (make-rat -2 3))

(print-rat (add-rat one-half one-half))