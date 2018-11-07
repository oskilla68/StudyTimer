# applescript code

osascript <<EOF
	do shell script "echo '\n0.0.0.0 www.facebook.com' >> /etc/hosts $*" with administrator privileges
	do shell script "echo '0.0.0.0 www.youtube.com' >> /etc/hosts $*" with administrator privileges
	do shell script "echo '0.0.0.0 mangadex.org' >> /etc/hosts $*" with administrator privileges
	do shell script "echo '0.0.0.0 www.jaiminisbox.com' >> /etc/hosts $*" with administrator privileges
	do shell script "echo '0.0.0.0 www.twitter.com' >> /etc/hosts $*" with administrator privileges
EOF
