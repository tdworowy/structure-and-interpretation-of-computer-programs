;(defn fold-left [op init sequence]
;  (letfn [(iter [result _rest]
;            (if (empty? _rest) result
;                (iter (op result (first _rest)) (rest _rest))))]
;    (iter init sequence)))

(defn fold-left [op init sequence]
  (if (empty? sequence)
    init
    (op (fold-left op init (rest sequence)) (first sequence))))

;(defn fold-right [op init sequence]
;  (letfn [(iter [_rest result]
;            (if (empty? _rest)
;              result
;              (op (first _rest) (iter (rest _rest) result))))]
;    (iter sequence init)))

(defn fold-right [op init sequence]
  (if (empty? sequence)
    init
    (op (first sequence) (fold-right op init (rest sequence)))))

(println (fold-right / 1 [1 2 3]))
(println (fold-left / 1 [1 2 3]))

(println (fold-right list '() [1 2 3]))
(println (fold-left  list '() [1 2 3]))

(println (fold-right * 1 [1 2 3]))
(println (fold-left * 1 [1 2 3]))

(defn reverse-right [sequence]
  (fold-right (fn [x acc] (conj acc x)) [] sequence))

(defn reverse-left [sequence]
  (fold-left conj '() sequence))

(println (reverse-right [1 2 3]))
(println (reverse-left [1 2 3]))
