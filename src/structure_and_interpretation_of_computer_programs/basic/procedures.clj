(defn square [x] (* x x))

(println (square 10))
(println (square (square 10)))

(defn sum-of-squares [x y] (+ (square x) (square y)))

(println (sum-of-squares 10 10))