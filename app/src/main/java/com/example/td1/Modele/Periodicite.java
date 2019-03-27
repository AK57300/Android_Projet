package com.example.td1.Modele;

public class Periodicite {

        private int nPeriodicite;
        private String periodicity;

        public int getnPeriodicite() {
            return nPeriodicite;
        }

        public void setnPeriodicite(int nPeriodicite) {
            this.nPeriodicite = nPeriodicite;
        }

        public String getPeriodicity() {
            return periodicity;
        }

        public void setPeriodicity(String periodicity) {
            this.periodicity = periodicity;
        }

        public Periodicite(int nPeriodicite, String periodicity) {
            this.nPeriodicite = nPeriodicite;
            this.periodicity = periodicity;
        }
    }


