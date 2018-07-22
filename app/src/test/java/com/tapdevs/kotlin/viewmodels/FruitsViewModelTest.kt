package com.tapdevs.kotlin.viewmodels

import com.tapdevs.kotlin.models.Fruit
import com.tapdevs.kotlin.views.fragments.FruitsFragment
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class FruitsViewModelTest {

    @Mock lateinit var fruitsFragment: FruitsFragment

    lateinit var viewModel: FruitsViewModel
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = FruitsViewModel(fruitsFragment)
    }

    @Test
    fun viewModel_NotNull() {
        assertNotNull(viewModel)
    }

    @Test
    fun getType_Verify() {

        val fruit : Fruit = Fruit().apply {
            type = "Apple"
            price = 14
            weight = 10
        }

        assertEquals("Type : Apple" ,viewModel.getType(fruit))
    }

    @Test
    fun getPrice_Verify() {

        val fruit : Fruit = Fruit().apply {
            type = "Apple"
            price = 14
            weight = 10
        }

        assertEquals("Price : 14" ,viewModel.getPrice(fruit))

    }

    @Test
    fun getWeight_Verify() {
        val fruit : Fruit = Fruit().apply {
            type = "Apple"
            price = 14
            weight = 10
        }

        assertEquals("Weight : 10" ,viewModel.getWeight(fruit))

    }
}