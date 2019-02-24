package com.cashcoding;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 1/25/2019.
 * This class is designed for categorizing positive application Testing

 */
@RunWith(Categories.class)
@Categories.IncludeCategory(PositiveCategoryTest.class)
@Categories.ExcludeCategory(NegativeCategoryTest.class)
@Suite.SuiteClasses({AppTest.class})

public class PositiveSuitTest {
}
