(ns structure-and-interpretation-of-computer-programs.exercises.chapter-2.counting-change2)

(defn cc [amount coin-values]
  (cond
    (= amount 0) 1
    (or (< amount 0) (empty? coin-values)) 0
    :else (+ (cc amount (rest coin-values)) (cc (- amount (first coin-values)) coin-values))))

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