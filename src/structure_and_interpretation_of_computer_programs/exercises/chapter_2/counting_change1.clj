(defn first-denomination [kinds-of-coins]
  (first kinds-of-coins))

(defn expect-first-denomination [kinds-of-coins]
  (rest kinds-of-coins))

(defn no-more? [coin-values]
  (empty? coin-values))

(defn cc [amount coin-values]
  (cond
    (= amount 0) 1
    (or (< amount 0) (no-more? coin-values)) 0
    :else (+ (cc amount (expect-first-denomination coin-values)) (cc (- amount (first-denomination coin-values)) coin-values))))

(def us-coins [50 25 10 5 1])
(def uk-coins [100 50 20 10 5 2 1 0.5])

(defn count-changes-us [amount]
  (cc amount us-coins))

(defn count-changes-uk [amount]
  (cc amount uk-coins))

(println (count-changes-us 100))
(println (count-changes-us 0))
(println (count-changes-us -1))
(println (count-changes-us 1))

(println (count-changes-uk 100))
(println (count-changes-uk 0))
(println (count-changes-uk -1))
(println (count-changes-uk 1))