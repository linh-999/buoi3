#!/bin/bash
set -e

# Wait for MySQL to start completely
echo "Waiting for MySQL to start..."
sleep 10

# Grant privileges to user
echo "Granting privileges to 'user'@'%'..."
mysql -uroot -proot123 -e "GRANT ALL PRIVILEGES ON mydb.* TO 'user'@'%'; FLUSH PRIVILEGES;"

echo "MySQL setup complete!"
