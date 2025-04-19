
(defn square [x] (* x x))

; tags
(defn attache-tag [type-tag contents]
  (cons type-tag contents))

(defn type-tag [datum]
  (if (seq? datum)
    (first datum)
    (println "Error Bad tagged datum -- TYPE-TAG" datum)))

(defn contents [datum]
  (if (seq? datum)
    (second datum)
    (println "Error Bad tagged datum -- CONTENTS" datum)))

(defn rectangular? [z]
  (= (type-tag z) 'rectangular))

(defn polar? [z]
  (= (type-tag z) 'polar))

; first representation
(defn make-from-real-img-rectangular [x y] (cons x y))
(defn make-from-mag-ang-rectangular [r a] (cons (* r (clojure.math/cos a)) (* r (clojure.math/sin a))))

(defn real-part-rectangular [z] (first z))
(defn img-part-rectangular [z] (second z))

(defn magnitude-rectangular [z] (clojure.math/sqrt (+ (square (real-part-rectangular z)) (square (img-part-rectangular z)))))
(defn angle-rectangular [z] (clojure.math/atan2 (img-part-rectangular z) (real-part-rectangular z)))

; second representation
(defn make-from-real-img-polar [x y] (cons (clojure.math/sqrt (+ (square x) (square y))) (clojure.math/atan2 y x)))
(defn make-from-mag-ang-polar [r a] (cons r a))

(defn magnitude-polar [z] (first z))
(defn angle-polar [z] (second z))

(defn real-part-polar [z] (* (magnitude-polar z) (clojure.math/cos (angle-polar z))))
(defn img-part-polar [z] (* (magnitude-polar z) (clojure.math/sin (angle-polar z))))

; generic
(defn real-part [z]
  (cond (rectangular? z) (real-part-rectangular (contents z))
        (polar? z) (real-part-polar (contents z))
        :else (println "Error: Unknowns type -- REAL-PART" z)))

(defn img-part [z]
  (cond (rectangular? z) (img-part-rectangular (contents z))
        (polar? z) (img-part-polar (contents z))
        :else (println "Error: Unknowns type -- IMAG-PART" z)))

(defn magnitude [z]
  (cond (rectangular? z) (magnitude-rectangular (contents z))
        (polar? z) (magnitude-polar (contents z))
        :else (println "Error: Unknowns type -- MAGNITUDE" z)))

(defn angle [z]
  (cond (rectangular? z) (angle-rectangular (contents z))
        (polar? z) (angle-polar (contents z))
        :else (println "Error: Unknowns type -- ANGLE" z)))

(defn make-from-real-img [x y]
  (make-from-real-img-rectangular x y))

(defn make-from-mag-ang [x y]
  (make-from-mag-ang-polar x y))

; operations
(defn add-complex [z1 z2]
  (make-from-real-img (+ (real-part z1) (real-part z2)) (+ (img-part z1) (img-part z2))))

(defn sub-complex [z1 z2]
  (make-from-real-img (- (real-part z1) (real-part z2)) (- (img-part z1) (img-part z2))))

(defn mul-complex [z1 z2]
  (make-from-mag-ang (* (magnitude z1) (magnitude z2)) (+ (angle z1) (angle z2))))

(defn div-complex [z1 z2]
  (make-from-mag-ang (/ (magnitude z1) (magnitude z2)) (- (angle z1) (angle z2))))

