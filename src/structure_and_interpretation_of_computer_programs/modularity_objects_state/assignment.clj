(ns structure-and-interpretation-of-computer-programs.modularity-objects-state.assignment)

(def balance (atom 100))

(defn withdraw1 [amount]
  (if (>= @balance amount)
    (swap! balance - amount)
    (println "Insufficient funds")))

(println (withdraw1 50))
(println (withdraw1 50))
(println (withdraw1 50))

(defn make-account [initial-balance]
  (let [balance (atom initial-balance)]
    (letfn [(withdraw [amount]
              (if (>= @balance amount)
                (swap! balance - amount)
                (println "Insufficient funds")))
            (deposit [amount]
              (swap! balance + amount))
            (dispatch [m]
              (cond
                (= m 'withdraw) withdraw
                (= m 'deposit) deposit
                :else (println "Unknown request")))]
      dispatch)))

(def w1 (make-account 100))

(println ((w1 'withdraw) 50))
(println ((w1 'withdraw) 50))
(println ((w1 'deposit) 50))