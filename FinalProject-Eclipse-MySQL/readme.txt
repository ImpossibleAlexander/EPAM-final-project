������ (Dynamic Web Project) ��� Eclipse IDE for Enterprise Java Developers.
�������� ������ �������� �������� ������ ���������� �������.

*********************************************************************

1. ��������� ������ MySQL.

2. ����� � ���������������� ������� � ��������� �������

	source ABSOLUTE_PATH_TO_SCRIPT;

��� ABSOLUTE_PATH_TO_SCRIPT - ���������� ���� � �����:

	sql/dbcreate-mysql.sql

3. ������� ������������ � ������ testuser, ������� testpass � ���� ��� ��� ����� �� ���� ������ st4db:

	grant all privileges on st4db.* to testuser@'%' identified by 'testpass';	
	
(��������� � ���������������� ������� MySQL)

4. ������� ������ � Eclipse.

5. ���������������� � Eclipse Apache Tomcat.
