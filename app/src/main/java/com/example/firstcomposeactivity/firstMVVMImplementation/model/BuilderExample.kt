package com.example.firstcomposeactivity.firstMVVMImplementation.model


/*var be = BuilderExample.Builder()
    .setAge("Abhishe Tripath")
    .setAge("29")

be.builder()*/
final class BuilderExample private constructor(var name: String?, var age: String?)
{
    class Builder
    {
        var name: String? = null
        var age: String? = null
        fun setAge(age: String): Builder
        {
            this.age = age
            return this
        }
        fun setName(name: String): Builder
        {
            this.name = name
            return this
        }

        fun builder(): BuilderExample
        {
            return BuilderExample(name, age)
        }
    }
}