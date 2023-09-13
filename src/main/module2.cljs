(ns module2
  (:require [module1]
            [module0]))

(defrecord My-record []
  module0/Proto
  (module0/method-1 [_ignoreme] :my-result))

(defn make-my-record [] (My-record.))