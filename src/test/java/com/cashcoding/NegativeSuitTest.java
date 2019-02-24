package com.cashcoding;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 1/25/2019.
 * This class is designed for categorizing positive application Testing
 */
@RunWith(Categories.class)
@Categories.IncludeCategory(NegativeCategoryTest.class)
@Categories.ExcludeCategory(PositiveCategoryTest.class)
@Suite.SuiteClasses({AppTest.class})

public class NegativeSuitTest {
}
