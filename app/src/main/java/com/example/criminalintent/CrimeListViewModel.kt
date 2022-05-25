package com.example.criminalintent

import androidx.lifecycle.ViewModel

class CrimeListViewModel : ViewModel() {

    private val crimeRepository = CrimeRepository.get()
    val crimes = crimeRepository.getCrimes()


}