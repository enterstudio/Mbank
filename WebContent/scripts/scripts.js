/**
 * 
 */
function showSomething()
{
	alert("Hello!");
}


function InputAndCommisionWithdraw()
{
	var messeage = confirm("You Will Be Charged commission Accordanly\n" +
			"To Mbank System Properties\n\n" +
			"Do Wish To Procceed With Transaction?\n\n" +
			"(You Can View System Properties\n" +
			"From EveryPage Using ViewSystemProperties)\n" +
	"Button");
	if(messeage==false)
	{
		return false;
	}
	else
	{
		var validation = true;
		var input = document.forms["withdrawFromAccount"]["sumOfAction"].value;
		if (input==null || input=="" || Number(input)<=0) 
		{
			validation = false;
			alert("Please Enter A Valid Sum Of Action(Positive Number)")
		}
		
		
		
		return validation;
	}
}
function InputAndCommisionDeposit()
{
	var messeage = confirm("You Will Be Charged commission Accordanly\n" +
			"To Mbank System Properties\n\n" +
			"Do Wish To Procceed With Transaction?\n\n" +
			"(You Can View System Properties\n" +
			"From EveryPage Using ViewSystemProperties)\n" +
	"Button");
	if(messeage==false)
	{
		return false;
	}
	else
	{
		var validation = true;
		var input = document.forms["DepositToAccount"]["sumOfAction"].value;
		if (input==null || input=="" || Number(input)<=0) 
		{
			validation = false;
			alert("Please Enter A Valid Sum Of Action(Positive Number)")
		}
		return validation;
	}
}

function udateDetailsValidation()
{
	var messeage = confirm("Are You Sure You Want To Procceed with Update?");
	if(messeage==false)
	{
		return false;
	}
	else
	{
		var validation = true;

		var updateAllFlag = false;

		var emailCheckBox =  document.forms["updateClientDetails"]["emailCheckBox"].checked;
		var phoneCheckBox = document.forms["updateClientDetails"]["phoneCheckBox"].checked;
		var addressCheckBox =document.forms["updateClientDetails"]["addressCheckBox"].checked;
		var updateAllCheckBox = document.forms["updateClientDetails"]["updateAll"].checked;

		if((emailCheckBox==false && phoneCheckBox==false 
				&& addressCheckBox==false && updateAllCheckBox==false) )
		{
			alert("Please Mark The Fields You Want to be updated!");
			return false;
		}

		else if(updateAllCheckBox==true)
		{
			updateAllFlag = true;
		}

		if(addressCheckBox==true || updateAllFlag==true)
		{
			var newAddress = document.forms["updateClientDetails"]["newAddress"].value;
			if (newAddress==null || newAddress=="") 
			{
				validation = false;
				alert("Please Enter A Address")
			}
		}
		if(emailCheckBox==true || updateAllFlag==true)
		{
			var email = document.forms["updateClientDetails"]["newEmail"].value;

			var atpos = email.indexOf("@");
			var dotpos = email.lastIndexOf(".");
			if (atpos< 1 || dotpos<atpos+2 || dotpos+2>=x.length) 
			{
				validation = false;
				alert("Not a valid e-mail address");
			}
		}

		if(phoneCheckBox==true || updateAllFlag==true)
		{
			var phone = document.forms["updateClientDetails"]["newPhone"].value;
			if(isNaN(phone))
			{
				validation = false;
				alert("Not Valid Phone Number");
			}
			else if(phone<9)
			{
				validation = false;
				alert("Not Valid Phone Number");
			}
		}
	}
	return validation;   
}


function InputAndCommisionCreateNewDeposit()
{
	var messeage = confirm("You Will Be Charged commission Accordanly\n" +
			"To Mbank System Properties\n\n" +
			"Do Wish To Procceed With Transaction?\n\n" +
			"(You Can View System Properties\n" +
			"From EveryPage Using ViewSystemProperties)\n" +
	"Button");
	if(messeage==false)
	{
		return false;
	}
	else
	{
		var validation = true;
		var input = document.forms["createNewDeposit"]["amountOfDeposit"].value;
		if (isNaN(input) || input==null || input=="" || Number(input)<=0) 
		{
			validation = false;
			alert("Please Enter A Valid amount Of Deposit(Positive Number)")
		}
		return validation;
	}
}



function confirmPreOpenDeposit()
{
	var messeage = confirm("Remember! Only Long Deposits Can Be Pre Opened\n" +
			"You Will Be Charged commission Accordanly\n" +
			"To Mbank System Properties\n\n" +
			"Do Wish To Procceed With Transaction?\n\n" +
			"(You Can View System Properties\n" +
			"From EveryPage Using ViewSystemProperties)\n" +
	"Button");
	if(messeage==false)
	{
		return false;
	}
	else
	{
		return true;
	}
}

