app.component('calculator',
{
    template:
    /*html*/
    `<display
        ref="displayRef"
        :displayValue="displayValue"
        class="display"
    ></display>
    <buttons @button-clicked="buttonClicked"></buttons>`,

    data()
    {
        return {
            displayValue: "0",
            isComma: false,
            previousAnswer: null,
            firstNumber: null,
            secondNumber: null,
            operator: -1, //0: pluss, 1: minus, 2: gange, 3: dele
            firstNewNumber: false, //Brukes til å sjekke om det andre tallet skal begynne
            operators: ["+", "-", "x", "/"]
        }
    },

    methods:
    {   
        buttonClicked(text)
        {
            switch (text)
            {
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    {
                        this.numberClicked(text); //Legger inn tallet som er trykt i displayet
                    }
                break; 
                case ".":
                    {
                        this.commaPressed(); //Legger inn et komma i displayet
                    }
                break;
                case "C":
                    {
                        this.clear(); //Fjerner alt, minne og hele pakka
                    }
                break;
                case "ANS":
                    {
                        this.fetchAnswer(); //Henter forrgie svar
                    }
                break;
                case "DEL":
                    {
                        this.delete(); //Sletter det som står i displayet nå
                    }
                break;
                case "+":
                case "-":            
                case "x":
                case "/":
                    {
                        this.setOperator(text); //Setter operatoren som skal brukes i utregningen
                    }
                break;
                case "=":
                    {
                        this.equalClicked(); //Starter utregningen og viser svaret i displayet
                    }
                break;
            }
        },
        
        numberClicked(number)
        {
            if (this.firstNewNumber)
            {
                this.displayValue = "";
                this.firstNewNumber = false;
                this.isComma = false;
            }
            if (this.displayValue != "0")
            {
                this.displayValue += number;
            }
            else
            {
                this.displayValue = number;
            }
        },

        commaPressed()
        {
            if (!this.isComma && !this.firstNewNumber)
            {
                this.displayValue += ".";
                this.isComma = true;
            }
        },

        clear()
        {
            this.displayValue = "0";
            this.isComma = false;
            this.previousAnswer = null;
            this.firstNumber = null;
            this.secondNumber = null;
            this.operator = -1;
            this.firstNewNumber = false;
        },

        fetchAnswer()
        {
            if (this.previousAnswer != null)
            {
                if (this.firstNumber == null)
                {
                    this.firstNumber = this.previousAnswer;
                    this.displayValue = this.previousAnswer;
                }
                else
                {
                    this.secondNumber = this.previousAnswer;
                    this.displayValue = this.previousAnswer;
                }
            }
        },

        delete()
        {
            this.displayValue = "0";
        },

        setOperator(operator)
        {
            switch (operator)
            {
                case "+":
                    {
                        this.operator = 0;
                    }
                break;
                case "-":
                    {
                        this.operator = 1;
                    }
                break;
                case "x":
                    {
                        this.operator = 2;
                    }
                break;
                case "/":
                    {
                        this.operator = 3;
                    }
                break;
                default:
                    console.log("setOperator() ble kalt, men argument var ugyldig, argument: " + operator);
                break;
            }
            this.firstNumber = this.displayValue;
            this.firstNewNumber = true;
            this.isComma = true;
        },

        equalClicked()
        {
            if (this.firstNumber == null || this.operator == -1)
            {

            }
            else
            {
                this.secondNumber = this.displayValue; //Setter tall to
                this.previousAnswer = this.calc(Number(this.firstNumber), Number(this.secondNumber), this.operator); // regner ut hva svaret er
                this.displayValue = String(this.previousAnswer); //Setter svaret på displayet
                
                this.$emit('new-entry',
                {
                    firstNumber: this.firstNumber,
                    secondNumber: this.secondNumber,
                    operator: this.operators[this.operator],
                    answer: this.previousAnswer
                });
                
                
                this.firstNumber = null;
                this.secondNumber = null; //Setter tall to til null
                this.isComma = false; //Setter at det ikke er noe komma her
                this.firstNewNumber = true;
            }
        },

        calc(number1, number2, operator)
        {
            switch (operator)
            {
                case 0:
                    {
                        return number1 + number2;
                    }
                break;
                case 1:
                    {
                        return number1 - number2;
                    }
                break;
                case 2:
                    {
                        return number1 * number2;
                    }
                break;
                case 3:
                    {
                        return number1 / number2;
                    }
                break;
                default:
                    console.log("calc() ble kalt, men argument var ugyldig, argument: " + number1 + ", " + number2 + ", " + operator);
                break;
            }
        }
    }
});