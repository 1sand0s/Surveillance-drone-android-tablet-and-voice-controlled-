void setup()
{
  pinMode(13,OUTPUT);
  pinMode(12,OUTPUT);
  pinMode(11,OUTPUT);
  pinMode(10,OUTPUT);
  pinMode(9,OUTPUT);
  Serial.begin(9600);
}
void loop()
{
  if(Serial.available()>0)
  {
    char c=Serial.read();
    int g=c-48;
   switch(g)
   {
     case 1:
     start();
     break;
    
     case 2:
     search();
     break;
     
     case 3:
     front();
     break;
     
     case 4:
     left();
     break;
     
     case 5:
     right();
     break;
     
     case 6:
     back();
     break;
   }
  }
}
void start()
{
      digitalWrite(13,HIGH);
      delay(1000);
      digitalWrite(13,LOW);
      delay(1000);
}
void search()
{
   digitalWrite(12,HIGH);
   digitalWrite(9,HIGH);
   delay(1000);
   digitalWrite(12,LOW);
   digitalWrite(9,LOW);
}
void front()
{
  digitalWrite(12,HIGH);
  digitalWrite(10,HIGH);
  delay(500);
  digitalWrite(12,LOW);
  digitalWrite(10,LOW);
}
void left()
{
  digitalWrite(10,HIGH);
  digitalWrite(11,HIGH);
  delay(500);
  digitalWrite(10,LOW);
  digitalWrite(11,LOW);
}
void right()
{
  digitalWrite(12,HIGH);
  digitalWrite(9,HIGH);
  delay(500);
  digitalWrite(12,LOW);
  digitalWrite(9,LOW);
}
void back()
{
  digitalWrite(11,HIGH);
  digitalWrite(9,HIGH);
  delay(500);
  digitalWrite(11,LOW);
  digitalWrite(9,LOW);
}

