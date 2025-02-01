(defn flatmap [proc seq]
  (apply concat (map proc seq)))

(defn prime? [n]
  (if (<= n 1)
    false
    (not-any? #(zero? (mod n %)) (range 2 (inc (Math/sqrt n))))))

(defn prime-sum? [pair]
  (prime? (+ (first pair) (first (rest pair)))))

(defn enumerate-interval [low high]
  (if (> low high) nil
      (cons low (enumerate-interval (+ low 1) high))))

(defn make-pair-sum [pair]
  (list (first pair) (first (rest pair)) (+ (first pair) (first (rest pair)))))

(defn unique-pairs [n]
  (flatmap
   (fn [i] (map (fn [j] (list i j))
                (enumerate-interval 1 (- i 1))))
   (enumerate-interval 1 n)))

(println (unique-pairs 5))

(defn prime-sum-pairs [n]
  (map make-pair-sum (filter prime-sum? (unique-pairs n))))

(println (prime-sum? [2 2]))
(println (make-pair-sum [2 2]))
(println (prime-sum-pairs 6))

(defn permutations1 [s]
  (if (empty? s)
    '(())
    (flatmap (fn [x]
               (map (fn [p] (cons x p))
                    (permutations1 (remove #(= x %) s))))
             s)))

(defn permutations2 [s]
  (if (empty? s)
    '(())
    (mapcat (fn [x]
              (map (fn [p] (cons x p))
                   (permutations2 (remove #(= x %) s))))
            s)))

(println (permutations1 [1 2 3]))
(println (permutations2 [1 2 3]))

