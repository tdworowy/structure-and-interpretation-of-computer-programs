
(defn square [x] (* x x))

; first representation
(defn make-from-real-img [x y] (cons x y))
(defn make-from-mag-ang [r a] (cons (* r (clojure.math/cos a)) (* r (clojure.math/sin a))))

(defn real-part [z] (first z))
(defn img-part [z] (second z))

(defn magnitude [z] (clojure.math/sqrt (+ (square (real-part z)) (square (img-part z)))))
(defn angle [z] (clojure.math/atan2 (img-part z) (real-part z)))

; second representation
(defn make-from-real-img [x y] (cons (clojure.math/sqrt (+ (square x) (square y))) (clojure.math/atan2 y x)))
(defn make-from-mag-ang [r a] (cons r a))

(defn magnitude [z] (first z))
(defn angle [z] (second z))

(defn real-part [z] (* (magnitude z) (clojure.math/cos (angle z))))
(defn img-part [z] (* (magnitude z) (clojure.math/sin (angle z))))

; operations
(defn add-complex [z1 z2]
  (make-from-real-img (+ (real-part z1) (real-part z2)) (+ (img-part z1) (img-part z2))))

(defn sub-complex [z1 z2]
  (make-from-real-img (- (real-part z1) (real-part z2)) (- (img-part z1) (img-part z2))))

(defn mul-complex [z1 z2]
  (make-from-mag-ang (* (magnitude z1) (magnitude z2)) (+ (angle z1) (angle z2))))

(defn div-complex [z1 z2]
  (make-from-mag-ang (/ (magnitude z1) (magnitude z2)) (- (angle z1) (angle z2))))

